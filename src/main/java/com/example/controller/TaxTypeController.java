package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.constants.Message;
import com.example.model.TaxType;
import com.example.service.TaxTypeService;

@Controller
@RequestMapping("/taxTypes")
public class TaxTypeController {

	@Autowired
	private TaxTypeService taxTypeService;

	@GetMapping
	public String index(Model model) {
		List<TaxType> all = taxTypeService.findAll();
		model.addAttribute("listTaxType", all);

		return "taxType/index";
	}

	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Long id) {
		if (id != null) {
			Optional<TaxType> taxType = taxTypeService.findOne(id);
			model.addAttribute(("taxType"), taxType.get());
		}
		return "taxType/show";
	}

	@GetMapping(value = "/new")
	public String create(Model model, @ModelAttribute TaxType entity) {
		model.addAttribute("taxType", entity);
		return "taxType/form";
	}

	@PostMapping
	public String create(@Validated @ModelAttribute TaxType entity, BindingResult result,
			RedirectAttributes redirectAttributes) {

		TaxType taxType = null;
		try {
			taxType = taxTypeService.save(entity);
			redirectAttributes.addFlashAttribute("success", Message.MSG_SUCESS_INSERT);
			return "redirect:/taxTypes/" + taxType.getId();
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute(("error"), Message.MSG_ERROR);
			return "redirect:/taxTypes";
		}
	}

	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Long id) {
		try {
			if (id != null) {
				Optional<TaxType> entity = taxTypeService.findOne(id);
				model.addAttribute(("taxType"), entity.get());
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "taxType/form";
	}

	@PutMapping
	public String update(@Validated @ModelAttribute TaxType entity, BindingResult result,
			RedirectAttributes redirectAttributes) {

		TaxType taxType = null;
		try {
			taxType = taxTypeService.save(entity);
			redirectAttributes.addFlashAttribute("success", Message.MSG_SUCESS_UPDATE);
			return "redirect:/taxTypes";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", Message.MSG_ERROR);
			e.printStackTrace();
			return "redirect:/taxTypes";
		}
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Optional<TaxType> entity = taxTypeService.findOne(id);
				taxTypeService.delete(entity.get());
				redirectAttributes.addFlashAttribute("success", Message.MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", Message.MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/taxTypes";
	}
}
