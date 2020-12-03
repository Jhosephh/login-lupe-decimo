package br.com.doce.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.doce.Model.Usuario;
import br.com.doce.Repositories.IUsuarioDAO;
import br.com.doce.Service.IUsuarioService;



@Service
public class UsuarioServiceImpl  implements IUsuarioService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 
	
	@Autowired
	private IUsuarioDAO usuarioDao;

	@Override
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	public Usuario registrar(Usuario u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return usuarioDao.save(u);
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	public Usuario buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		usuarioDao.deleteById(id);
	}
	
	

  

}
