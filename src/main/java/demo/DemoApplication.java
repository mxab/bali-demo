package demo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	NoteRepo repo;

	@ResponseBody
	@RequestMapping("/notes")
	public List<Note> hello() {
		Note note = new Note();
		note.setDate(new Date());
		note.setMessage("Someone was here...");

		repo.save(note);

		return repo.findAll();
	}

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute(repo.findAll());
		return "index";
	}
}
