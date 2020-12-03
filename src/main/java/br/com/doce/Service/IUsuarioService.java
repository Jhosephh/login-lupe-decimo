package br.com.doce.Service;

import java.util.List;

import br.com.doce.Model.Usuario;




public interface IUsuarioService {
	public Usuario findByUsername(String username); 
	public Usuario registrar(Usuario u);
	public List<Usuario> listar();
	public Usuario buscarPorId(Long id);
	public void eliminar(Long id); 
	
}
