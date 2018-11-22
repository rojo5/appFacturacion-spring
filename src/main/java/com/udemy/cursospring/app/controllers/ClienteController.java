package com.udemy.cursospring.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.udemy.cursospring.app.models.dao.IClienteDao;
import com.udemy.cursospring.app.models.entity.Cliente;

@Controller
@SessionAttributes("cliente") // Guarda los datos del objeto cliente en la sesion
public class ClienteController {

	@Autowired //Activa el bean en el contexto
	private IClienteDao clienteDao;
	
	@RequestMapping(value="/listar", method= RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteDao.findAll()); // Devuelve un listado de los clientes
		return "listar";
	}
	
	@RequestMapping(value="/form")
	public String crear (Map<String, Object> model) { //Puedes poner como parametro el objeto Model o el Map propio de java con un Objeto generico
		
		Cliente cliente= new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario del cliente");
		return "form";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Cliente cliente = null;
		
		if(id>0) {
			cliente = clienteDao.findOne(id);
		} else {
			return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Editar cliente");
		return "form";
		
	}
	
	@RequestMapping(value="/form", method= RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario del cliente");
			return "form";
		}
		clienteDao.save(cliente);
		status.setComplete(); //Borra los datos de la sesion
		return "redirect:listar"; //Redirge la web hacia la pagina de listar
	}
	
	@RequestMapping(value="/eliminar/{id})")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id >0) {
			clienteDao.delete(id);
		}
		
		return "redirect:/listar";
	}
}
