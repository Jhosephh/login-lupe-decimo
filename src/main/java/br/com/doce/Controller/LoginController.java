package br.com.doce.Controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import uttt.web.entity.Usuario;
import uttt.web.service.IUsuarioService;

@Controller
public class LoginController {

	@Autowired
	private IUsuarioService usuarioService;
	
	
	@GetMapping("list")
	public String listar(Model model) {
		List<Usuario>usuario=usuarioService.listar();
		model.addAttribute("usuario", usuario);
		return "indexUsuario";
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute Usuario usuario, BindingResult result,Model model) {
			
		if (result.hasErrors()) {
				
			model.addAttribute("titulo", "Formulario: Nuevo Usuario");
			model.addAttribute("usuario", usuario);
			
			return "formUsuario";
		}
		
		usuarioService.registrar(usuario);
		System.out.println("usuario guardado con exito");
		return "redirect:/list";
	
	}
	
	
	
	
	@GetMapping("/editar/{id}")
	public String actualizar(@PathVariable("id") Long idUsuario, Model model) {
		
		Usuario usuario= usuarioService.buscarPorId(idUsuario);
		
		
		
		model.addAttribute("titulo", "Formulario: Editar Usuario");
		model.addAttribute("usuario", usuario);
		
		
		return "formUsuario";
	
	}
	
	@GetMapping("/delet/{id}")
	public String Delete(@PathVariable("id") Long idUsuario) {
		
		usuarioService.eliminar(idUsuario);;
		
		
		return "redirect:/list";
	
	}
	
	
	
	
	
	
	@GetMapping("/auth/login")
	public String login(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		
		return "login"; 
	}
	
	
	
	
	@GetMapping("/auth/registro")
	public String registroForm(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		
		return "registro"; 
	}
	
	@PostMapping("/auth/registro")
	public String registro(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return"redirect:/auth/registro";
		}
		else {
			model.addAttribute("usuario", usuarioService.registrar(usuario));
		}
		return "redirect:/auth/login";
	}

}
