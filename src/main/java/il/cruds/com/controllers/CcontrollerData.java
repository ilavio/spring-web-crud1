package il.cruds.com.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import il.cruds.com.dao.PersonDAO;
import il.cruds.com.personal.Personal;
import jakarta.validation.Valid;

@Controller()
@RequestMapping("/datas")
public class CcontrollerData {
	
	private PersonDAO person;
	
	@Autowired
	 public CcontrollerData (PersonDAO person) {
		this.person = person;
	}
	
	@GetMapping()
	public String index(Model model) throws SQLException{
		model.addAttribute("model1", person.index());
		return "index";
	}
	
	@GetMapping("/{id}")
	public String show (@PathVariable("id") int id, Model model) {
		model.addAttribute("model2", person.show(id));
		return "show";
	}

	@GetMapping("/new")
	public String newDatas (Model model) { // вставляем модель с пустым объектом класса Personal
		model.addAttribute("model3", new Personal()); 
		return "new"; // и открываем представление с текстовым полем для ввода данных
	}

	@PostMapping()
	public String create(@ModelAttribute("model3") @Valid Personal newPers, BindingResult bindingResult ) { // перехватываем model3 и передаем объект 

		if(bindingResult.hasErrors()) { 
			return "new"; }

		person.save(newPers); // с заполнеными данными и вкладываем в список
		return"redirect:/datas"; // перекидываем на ссылку /datas
	}

	@GetMapping("/{id}/edit")
	public String edit (Model model, @PathVariable("id") int id) { //изымаем id из адреса http
		model.addAttribute("persEdit", person.show(id));
		return "edit";
	}

	@PatchMapping("/{id}") // когда в адрес http вставляется переменная
	public String update (@ModelAttribute("persEdit") @Valid Personal upPers, BindingResult bindingResult,
			@PathVariable("id") int id ) { // перехватываем persEdit

		if(bindingResult.hasErrors()) { return "edit"; } // 

		person.update(id, upPers.getName(),
		upPers.getSurname(), upPers.getTelephone(), upPers.getPatronymic(), upPers.getDescription(), upPers.getD_name(),
		upPers.getD_surname(), upPers.getD_patronymic(), upPers.getD_specialization(), upPers.getDatas());
		return "redirect:/datas";
	}

	@DeleteMapping("/{id}") 
	public String delete (@PathVariable("id") int id) {
		person.delete(id);
		return "redirect:/datas";
	}
	
}
