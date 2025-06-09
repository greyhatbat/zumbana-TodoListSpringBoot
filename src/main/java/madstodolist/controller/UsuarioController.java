package madstodolist.controller;

import madstodolist.dto.UsuarioData;
import javax.servlet.http.HttpSession;
import madstodolist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/registrados")
    public String listadoUsuarios(Model model, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("idUsuarioLogeado");

        UsuarioData usuario = usuarioService.findById(idUsuario);
        if (usuario == null || !Boolean.TRUE.equals(usuario.getEsAdmin())) {
            model.addAttribute("error", "Acceso no autorizado");
            return "accesoDenegado";
        }

        List<UsuarioData> usuarios = usuarioService.allUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }
    
    /*public String listadoUsuarios(Model model) {
        List<UsuarioData> usuarios = usuarioService.allUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }*/
    
   
    
    @GetMapping("/registrados/{id}")
    public String descripcionUsuario(@PathVariable Long id, Model model, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("idUsuarioLogeado");

        UsuarioData usuarioLogeado = usuarioService.findById(idUsuario);
        if (usuarioLogeado == null || !Boolean.TRUE.equals(usuarioLogeado.getEsAdmin())) {
            model.addAttribute("error", "Acceso no autorizado");
            return "accesoDenegado";
        }

        UsuarioData usuario = usuarioService.findById(id);
        model.addAttribute("usuario", usuario);
        return "usuario";
    }
    /*public String descripcionUsuario(@PathVariable Long id, Model model) {
        UsuarioData usuario = usuarioService.findById(id);
        model.addAttribute("usuario", usuario);
        return "usuario";
    }*/
    

}
