/*
 * @author Mayank
 */
package in.co.rays.project_0.ctl;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.rays.project_0.dto.StudentDTO;
import in.co.rays.project_0.exception.DuplicateRecordException;
import in.co.rays.project_0.form.CollegeForm;
import in.co.rays.project_0.form.StudentForm;
import in.co.rays.project_0.service.CollegeServiceInt;
import in.co.rays.project_0.service.StudentServiceInt;

/**
 * The Class StudentCtl.
 */
@Controller
@RequestMapping(value = "/ctl/Student")
public class StudentCtl extends BaseCtl {

	/** The service. */
	@Autowired
	private StudentServiceInt service;
	
	/** The message source. */
	@Autowired
	private MessageSource messageSource;
	
	/** The cservice. */
	@Autowired
	private CollegeServiceInt cservice;

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.ctl.BaseCtl#preload(org.springframework.ui.Model)
	 */
	public void preload(Model model) {
		List list = cservice.search(null);
		model.addAttribute("collegeList", list);
	}

	/**
	 * Display.
	 *
	 * @param id the id
	 * @param form the form
	 * @param model the model
	 * @param locale the locale
	 * @return the string
	 */
	@RequestMapping(value = "/AddStudent", method = RequestMethod.GET)
	public String display(@RequestParam(required = false) Long id, @ModelAttribute("form") StudentForm form,
			Model model, Locale locale) {
		String enteremail = messageSource.getMessage("label.enteremail", null, locale);
		model.addAttribute("enteremail", enteremail);

		String enterdob = messageSource.getMessage("label.enterdob", null, locale);
		model.addAttribute("enterdob", enterdob);

		String enterfirstName = messageSource.getMessage("label.enterfname", null, locale);
		model.addAttribute("enterfirstName", enterfirstName);

		String enterLastName = messageSource.getMessage("label.enterlname", null, locale);
		model.addAttribute("enterLastName", enterLastName);

		String enterMobile = messageSource.getMessage("label.entermob", null, locale);
		model.addAttribute("enterMobile", enterMobile);

		if (id != null && id > 0) {
			StudentDTO dto = service.findByPK(id);
			form.populate(dto);
		}

		return "StudentView";

	}

	/**
	 * Submit.
	 *
	 * @param id the id
	 * @param form the form
	 * @param bindresult the bindresult
	 * @param model the model
	 * @param locale the locale
	 * @return the string
	 */
	@RequestMapping(value = "/AddStudent", method = RequestMethod.POST)

	public String submit(@RequestParam(required = false) Long id, @ModelAttribute("form") @Valid StudentForm form,
			BindingResult bindresult, Model model, Locale locale) {
		String enteremail = messageSource.getMessage("label.enteremail", null, locale);
		model.addAttribute("enteremail", enteremail);

		String enterdob = messageSource.getMessage("label.enterdob", null, locale);
		model.addAttribute("enterdob", enterdob);

		String enterfirstName = messageSource.getMessage("label.enterfname", null, locale);
		model.addAttribute("enterfirstName", enterfirstName);

		String enterLastName = messageSource.getMessage("label.enterlname", null, locale);
		model.addAttribute("enterLastName", enterLastName);

		String enterMobile = messageSource.getMessage("label.entermob", null, locale);
		model.addAttribute("enterMobile", enterMobile);

		if (OP_SAVE.equalsIgnoreCase(form.getOperation()) || OP_UPDATE.equalsIgnoreCase(form.getOperation())) {
			if (bindresult.hasErrors()) {
				System.out.println("----------------" + bindresult.getAllErrors());
				return "StudentView";
			}

			System.out.println("in post method" + form.getOperation());
			StudentDTO dto = (StudentDTO) form.getDto();

			dto.setCreatedBy("root");
			dto.setModifiedBy("root");
			if (dto.getId() > 0) {
				service.update(dto);
				String msg = messageSource.getMessage("message.updatesuccess", null, locale);
				model.addAttribute("success", msg);
			} else {
				try {
					System.out.println("in add method of student");
					service.add(dto);
					String msg = messageSource.getMessage("message.success", null, locale);
					model.addAttribute("success", msg);
				} catch (DuplicateRecordException e) {
					String msg = messageSource.getMessage("message.email", null, locale);
					model.addAttribute("error", msg);
				}
			}
		}

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/Student/AddStudent";
		}
		if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/Student/StudentListCtl";
		}

		return "StudentView";
	}

	/**
	 * Display.
	 *
	 * @param form the form
	 * @param model the model
	 * @param locale the locale
	 * @return the string
	 */
	@RequestMapping(value = "/StudentListCtl", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") StudentForm form, Model model, Locale locale) {
		String enterfirstName = messageSource.getMessage("label.enterfname", null, locale);
		model.addAttribute("enterfirstName", enterfirstName);
		String enterLastName = messageSource.getMessage("label.enterlname", null, locale);
		model.addAttribute("enterLastName", enterLastName);
		List list = service.search(new StudentDTO(), form.getPageNo(), form.getPageSize());
		model.addAttribute("list", list);
		List nextList = service.search(new StudentDTO(), form.getPageNo() + 1, form.getPageSize());
		model.addAttribute("nextlistsize", nextList.size());
		return "StudentListView";

	}

	/**
	 * Submit.
	 *
	 * @param operation the operation
	 * @param form the form
	 * @param model the model
	 * @param locale the locale
	 * @return the string
	 */
	@RequestMapping(value = "/StudentListCtl", method = RequestMethod.POST)
	public String submit(@RequestParam(required = false) String operation, @ModelAttribute("form") StudentForm form,
			Model model, Locale locale) {
		String sno = messageSource.getMessage("label.sno", null, locale);
		model.addAttribute("sno", sno);
		String enterfirstName = messageSource.getMessage("label.enterfname", null, locale);
		model.addAttribute("enterfirstName", enterfirstName);

		String enterLastName = messageSource.getMessage("label.enterlname", null, locale);
		model.addAttribute("enterLastName", enterLastName);
		int pageNo = form.getPageNo();

		if (OP_PREVIOUS.equalsIgnoreCase(operation)) {
			pageNo--;
		} else if (OP_NEXT.equalsIgnoreCase(operation)) {
			pageNo++;
		} else if (OP_DELETE.equalsIgnoreCase(operation)) {
			if (form.getChk_1() != null) {
				System.out.println("in delete operation");
				for (long id : form.getChk_1()) {
					service.delete(id);
				}
				String msg = messageSource.getMessage("message.deleterecord", null, locale);
				model.addAttribute("success", msg);
			} else {
				String msg = messageSource.getMessage("message.atleastone", null, locale);
				model.addAttribute("error", msg);
			}
		}
		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/Student/StudentListCtl";
		}
		if (OP_NEW.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/Student/AddStudent";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		form.setPageNo(pageNo);

		StudentDTO dto = (StudentDTO) form.getDto();

		List list = service.search(dto, pageNo, form.getPageSize());
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", messageSource.getMessage("message.norecord", null, locale));
		}

		List next = service.search(dto, pageNo + 1, form.getPageSize());
		model.addAttribute("nextlistsize", next.size());

		return "StudentListView";
	}

}
