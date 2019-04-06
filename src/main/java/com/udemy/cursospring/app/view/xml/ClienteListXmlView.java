package com.udemy.cursospring.app.view.xml;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.udemy.cursospring.app.models.entity.Cliente;

@Component("listar.xml")
public class ClienteListXmlView  extends MarshallingView{

	@Autowired
	public ClienteListXmlView(Jaxb2Marshaller marshaller) {
		super(marshaller);
		
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//Vaciamos el model para que no aparezcan en el xml
		model.remove("titulo");
		model.remove("page");
		//Obtenemos el listado de cliente
		Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");
		//vaciamos el model
		model.remove("clientes");
		
		model.put("clienteList", new ClienteList(clientes.getContent()));
		
		
		super.renderMergedOutputModel(model, request, response);
	}
	
	

}
