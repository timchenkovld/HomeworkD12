package com.example.homeworkD12.service;

import com.example.homeworkD12.entity.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class NoteServiceImpl implements NoteService {

    private final List<Note> notes = new ArrayList<>();

    @Override
    public List<Note> listAll() {
        return notes;
    }

    @Override
    public Note add(Note note) {
        note.setId(generateUniqueId());
        notes.add(note);
        return note;
    }

    @Override
    public void deleteById(long id) {
        notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst()
                .ifPresent(notes::remove);
    }

    @Override
    public void update(Note note) {
        Optional<Note> optionalNote = notes.stream()
                .filter(n -> n.getId().equals(note.getId()))
                .findFirst();
        if (optionalNote.isPresent()) {
            optionalNote.get().setTitle(note.getTitle());
            optionalNote.get().setContent(note.getContent());
        } else {
            throw new RuntimeException("Note with id " + note.getId() + " not found");
        }
    }

    @Override
    public Note getById(long id) {
        return notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Note with id " + id + " not found"));
    }

    private Long generateUniqueId() {
        return new Random().nextLong();
    }
}
