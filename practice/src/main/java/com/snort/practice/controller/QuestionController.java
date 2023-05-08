package com.snort.practice.controller;

import com.snort.practice.entity.Question;
import com.snort.practice.request.QuestionRequest;
import com.snort.practice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
//@RestController
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }
/*
    @PostMapping("/mcq/saveQuestion")
    public Question createQuestion(@RequestBody QuestionRequest questionRequest) {
        Question question = questionService.createQuestion(questionRequest);
        return question;
    }*/
    @GetMapping("/mcq/createQuestion")
    public String showCreateQuestionForm(Model model) {
        model.addAttribute("questionRequest", new QuestionRequest());
        return "createQuestion";
    }
    @PostMapping("/mcq/saveQuestion")
    public String saveQuestion(@ModelAttribute("questionRequest") QuestionRequest questionRequest) {
        questionService.createQuestion(questionRequest);
        return "redirect:/mcq/createQuestion";
    }



    @GetMapping("/mcq/findByCategoryAndLevel/{category}/{level}")
    public List<Question> findByCategoryAndLevel(@PathVariable String category, @PathVariable String level) {
        List<Question> byCateoryAndLevel = questionService.findByCateoryAndLevel(category, level);
        return byCateoryAndLevel;
    }
    /*@GetMapping("/startExamsDemo")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
       Question question = new Question();
        model.addAttribute("question", question);
        return "startExamsDemo";
    }*/
  /*  @GetMapping("/mcq/exam")
    public String startExam(Model model, @RequestParam(required = false, name = "category") String category,
                            @RequestParam(required = false, name = "level") String level) {
        if (category != null && level != null) {
            List<Question> questions = questionService.findByCateoryAndLevel(category, level);
            model.addAttribute("questions", questions);
            System.out.println("List of question: "+questions);
            return "exam";
        }
        return "startExamsDemo";
    }*/
    @GetMapping("/mcq/exam")
    public String startExam(Model model, @RequestParam(required = false, name = "category") String category,
                            @RequestParam(required = false, name = "level") String level) {
        if (category != null && level != null) {
            List<Question> questions = questionService.findByCateoryAndLevel(category, level);
            model.addAttribute("questions", questions);
            return "exam";
        } else {
            return "startExamsDemo";
        }
    }

    @GetMapping("/mcq/exam12")
    public String startExam1(Model model) {
        return "startExamsDemo";
    }
    @DeleteMapping("/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable Long id){
        if (id!=null){
            questionService.deleteQuestion(id);
        return "Questions with id : "+id+ "Deleted Successfully";
        }
        return "Questions with id  :"+id+ "Unable to delete";
    }
}