package com.example.homeworkD12;

import com.example.homeworkD12.entity.Note;
import com.example.homeworkD12.service.NoteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
class HomeworkD12ApplicationTests {

    @Autowired
    private NoteServiceImpl noteService;

    @Test
    void testAdd() {
        Note newNote = new Note(1L, "Test Title", "Test Content");
        Note addedNote = noteService.add(newNote);
        Assertions.assertNotNull(addedNote.getId());
        Assertions.assertEquals(newNote.getTitle(), addedNote.getTitle());
        Assertions.assertEquals(newNote.getContent(), addedNote.getContent());
    }

    @Test
    void testGetById() {
        Note addedNote = noteService.add(new Note(1L, "Test Title", "Test Content"));
        Note retrievedNote = noteService.getById(addedNote.getId());
        Assertions.assertNotNull(retrievedNote);
        Assertions.assertEquals(addedNote.getId(), retrievedNote.getId());
        Assertions.assertEquals(addedNote.getTitle(), retrievedNote.getTitle());
        Assertions.assertEquals(addedNote.getContent(), retrievedNote.getContent());
    }

    @Test
    void testUpdate() {
        Note addedNote = noteService.add(new Note(1L, "Test Title", "Test Content"));
        addedNote.setId(5L);
        addedNote.setTitle("Test Title12314");
        addedNote.setContent("Test Content213213");
        noteService.update(addedNote);
        Note updatedNote = noteService.getById(addedNote.getId());
        Assertions.assertNotNull(updatedNote);
        Assertions.assertEquals(addedNote.getId(), updatedNote.getId());
        Assertions.assertEquals(addedNote.getTitle(), updatedNote.getTitle());
        Assertions.assertEquals(addedNote.getContent(), updatedNote.getContent());
    }

    @Test
    @DirtiesContext
    void testDelete() {
        Note newNote = new Note(1L, "Test Title", "Test Content");
        Note addedNote = noteService.add(newNote);
        Assertions.assertNotNull(addedNote.getId());
        noteService.deleteById(addedNote.getId());
        Assertions.assertThrows(RuntimeException.class, () -> noteService.getById(addedNote.getId()));
    }

    @Test
    void testListAllNotes() {
        noteService.add(new Note(1L, "Title 1", "Content 1"));
        noteService.add(new Note(2L, "Title 2", "Content 2"));
        List<Note> allNotes = noteService.listAll();
        Assertions.assertEquals(4, allNotes.size());
    }
}
