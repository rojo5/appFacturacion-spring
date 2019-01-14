package com.udemy.cursospring.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udemy.cursospring.app.models.entity.Cliente;
import com.udemy.cursospring.app.models.service.IClienteService;
import com.udemy.cursospring.app.models.service.IUploadFileService;
import com.udemy.cursospring.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente") // Guarda los datos del objeto cliente en la sesion
public class ClienteController {

	@Autowired // Activa el bean en el contexto
	private IClienteService clienteService;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping(value = "/uploads/{filename:.+}") // Esto ":.+" es para que no trunque las extensiones de los archivos
													// en las url
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		model.put("cliente", cliente);
		model.put("titulo", "Detalle cliente: " + cliente.getNombre());
		return "ver";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = new PageRequest(page, 4);

		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes); // Devuelve un listado de los clientes
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) { // Puedes poner como parametro el objeto Model o el Map propio de
														// java con un Objeto generico

		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario del cliente");
		return "form";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;

		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El ID de cliente no existe");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID de cliente no puede ser 0");
			return "redirect:/listar";
		}

		model.put("cliente", cliente);
		model.put("titulo", "Editar cliente");
		return "form";

	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario del cliente");
			return "form";
		}

		if (!foto.isEmpty()) {

			if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null
					&& cliente.getFoto().length() > 0) {

				uploadFileService.delete(cliente.getFoto());

			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Foto " + uniqueFilename + " subida correctamente");

			cliente.setFoto(uniqueFilename);
		}

		String mensajeFlash = (cliente.getId() != null) ? "Cliente editado correctamente"
				: "Cliente creado correctamente";

		clienteService.save(cliente);
		status.setComplete(); // Borra los datos de la sesion
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar"; // Redirge la web hacia la pagina de listar
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {

			Cliente cliente = clienteService.findOne(id);

			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado correctamente");

			if (uploadFileService.delete(cliente.getFoto())) {
				flash.addFlashAttribute("info", "Foto: " + cliente.getFoto() + "Ha sido eleminada correctamente");
			}

		}

		return "redirect:/listar";
	}
}
