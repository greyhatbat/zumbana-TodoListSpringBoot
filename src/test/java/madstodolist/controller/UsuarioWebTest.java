package madstodolist.controller;

import madstodolist.dto.UsuarioData;
import madstodolist.service.UsuarioService;
import madstodolist.model.Usuario;
import madstodolist.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void servicioLoginUsuarioOK() throws Exception {
        UsuarioData anaGarcia = new UsuarioData();
        anaGarcia.setNombre("Ana García");
        anaGarcia.setId(1L);

        when(usuarioService.login("ana.garcia@gmail.com", "12345678"))
                .thenReturn(UsuarioService.LoginStatus.LOGIN_OK);
        when(usuarioService.findByEmail("ana.garcia@gmail.com"))
                .thenReturn(anaGarcia);

        this.mockMvc.perform(post("/login")
                        .param("eMail", "ana.garcia@gmail.com")
                        .param("password", "12345678"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/usuarios/1/tareas"));
    }

    @Test
    public void servicioLoginUsuarioNotFound() throws Exception {
        when(usuarioService.login("pepito.perez@gmail.com", "12345678"))
                .thenReturn(UsuarioService.LoginStatus.USER_NOT_FOUND);

        this.mockMvc.perform(post("/login")
                        .param("eMail", "pepito.perez@gmail.com")
                        .param("password", "12345678"))
                .andExpect(content().string(containsString("No existe usuario")));
    }

    @Test
    public void servicioLoginUsuarioErrorPassword() throws Exception {
        when(usuarioService.login("ana.garcia@gmail.com", "000"))
                .thenReturn(UsuarioService.LoginStatus.ERROR_PASSWORD);

        this.mockMvc.perform(post("/login")
                        .param("eMail", "ana.garcia@gmail.com")
                        .param("password", "000"))
                .andExpect(content().string(containsString("Contraseña incorrecta")));
    }

    @Test
    public void listadoUsuariosSoloParaAdminSeMuestraCorrectamente() throws Exception {
        Usuario usuarioAdmin = new Usuario();
        usuarioAdmin.setEmail("admin@test.com");
        usuarioAdmin.setPassword("123");
        usuarioAdmin.setNombre("Admin");
        usuarioAdmin.setEsAdmin(true);
        usuarioRepository.save(usuarioAdmin);

        usuarioAdmin = usuarioRepository.findByEmail("admin@test.com").orElseThrow();

        mockMvc.perform(get("/registrados")
                .sessionAttr("idUsuarioLogeado", usuarioAdmin.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Usuarios registrados")))
                .andExpect(content().string(containsString("admin@test.com")));
    }

    @Test
    public void descripcionUsuarioSoloParaAdminSeMuestraCorrectamente() throws Exception {
        Usuario admin = new Usuario();
        admin.setEmail("admin@todolist.com");
        admin.setPassword("123");
        admin.setNombre("Admin");
        admin.setEsAdmin(true);
        usuarioRepository.save(admin);

        Usuario usuario = new Usuario();
        usuario.setEmail("usuario@todolist.com");
        usuario.setPassword("abc");
        usuario.setNombre("Usuario");
        usuario.setEsAdmin(false);
        usuarioRepository.save(usuario);

        admin = usuarioRepository.findByEmail("admin@todolist.com").orElseThrow();
        usuario = usuarioRepository.findByEmail("usuario@todolist.com").orElseThrow();

        mockMvc.perform(get("/registrados/" + usuario.getId())
                .sessionAttr("idUsuarioLogeado", admin.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("usuario@todolist.com")))
                .andExpect(content().string(containsString("Usuario")));
    }

    @Test
    public void accesoDenegadoParaUsuarioNoAdmin() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("normal@test.com");
        usuario.setPassword("pass");
        usuario.setNombre("Normal User");
        usuario.setEsAdmin(false);
        usuarioRepository.save(usuario);

        usuario = usuarioRepository.findByEmail("normal@test.com").orElseThrow();

        mockMvc.perform(get("/registrados")
                .sessionAttr("idUsuarioLogeado", usuario.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Acceso no autorizado")));
    }
}
