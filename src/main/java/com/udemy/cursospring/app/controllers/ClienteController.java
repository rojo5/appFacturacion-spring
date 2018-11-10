package com.udemy.cursospring.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.udemy.cursospring.app.models.dao.IClienteDao;
import com.udemy.cursospring.app.models.entity.Cliente;

@Controller
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
	
	@RequestMapping(value="/form", method= RequestMethod.POST)
	public String guardar(Cliente cliente) {
		clienteDao.save(cliente);
		return "redirect:listar"; //Redirge la web hacia la pagina de listar
	}
}
