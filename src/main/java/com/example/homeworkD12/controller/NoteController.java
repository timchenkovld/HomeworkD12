package com.example.homeworkD12.controller;

import com.example.homeworkD12.service.dto.NoteDto;
import com.example.homeworkD12.service.exception.NoteNotFoundException;
import com.example.homeworkD12.service.mapping.NoteMapper;
import com.example.homeworkD12.service.service.NoteService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/note")
public class NoteController {
    private NoteService noteService;
    private NoteMapper noteMapper;

    public NoteController(NoteService noteService, NoteMapper noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    @GetMapping("/list")
    public ModelAndView noteList() {
        ModelAndView result = new ModelAndView("note/allNotes");
        result.addObject("notes", noteMapper.toNoteResponses(noteService.listAll()));
        return result;
    }

    @GetMapping("/edit")
    public ModelAndView getNoteForEdit(@NotEmpty @RequestParam(value = "id") String id) throws NoteNotFoundException {
        UUID uuid = UUID.fromString(id);
        ModelAndView result = new ModelAndView("note/updateNotes");
        result.addObject("note", noteMapper.toNoteResponse(noteService.getById(uuid)));
        return result;
    }

    @PostMapping("/create")
    public ModelAndView createNote(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") @NotBlank String content) {
        NoteDto dto = new NoteDto();
        dto.setTitle(title);
        dto.setContent(content);
        noteService.add(dto);
        return noteList();
    }

    @PostMapping("/update")
    public ModelAndView updateNote(
            @NotNull @RequestParam(value = "id") String id,
            @RequestParam(value = "title") String title,
            @NotEmpty @RequestParam(value = "content") String content) throws NoteNotFoundException {
        NoteDto dto = new NoteDto();
        dto.setId(UUID.fromString(id));
        dto.setTitle(title);
        dto.setContent(content);
        noteService.update(dto);
        return noteList();
    }

    @PostMapping("/delete")
    public ModelAndView deleteNote(@RequestParam("id") UUID id) throws NoteNotFoundException {
        noteService.deleteById(id);
        return noteList();
    }
}
