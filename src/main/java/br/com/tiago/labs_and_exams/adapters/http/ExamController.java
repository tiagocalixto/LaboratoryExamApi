package br.com.tiago.labs_and_exams.adapters.http;


import br.com.tiago.labs_and_exams.entities.contract.Exam;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.response.ResponseApi;
import br.com.tiago.labs_and_exams.useCase.interfaces.ExamUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/exam")
@Slf4j
public class ExamController {

    @Autowired
    ExamUseCase useCases;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody Exam exam) {

        log.info("Start Create a exam...");
        Exam contract = useCases.insert(exam);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.CREATED.name(), "Successfully Created!", contract);
        log.info("Response: {}", contract.toString());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/createInBlock")
    public ResponseEntity<?> createInBlock(@RequestBody List<Exam> exams) {

        log.info("Start Create a  list of Exams...");
        List<Exam> contracts = useCases.insertInBlock(exams);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.CREATED.name(), "Successfully Created!", contracts);
        log.info("Response: {}", contracts.toString());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id,@RequestBody Exam exam) {

        log.info("Start Update a exam...");
        Exam contract = useCases.update(exam, id);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Updated!", contract);
        log.info("Response: {}", contract.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/putInBlock")
    public ResponseEntity<?> updateInBlock(@RequestBody  List<Exam> exams) {

        log.info("Start Update a list of exams...");
        List<Exam> contracts = useCases.updateInBlock(exams);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Updated!", contracts);
        log.info("Response: {}", contracts.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable(value = "name") String name) {

        log.info("Start Search ID of Exam...");
        Exam contract = useCases.getByName(name);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Found!", contract);
        log.info("Response: {}", contract.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> findAll() {

        log.info("Start Search all a exams...");
        List<Exam> contract = useCases.getAll();
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Found!", contract);
        log.info("Response: {}", contract.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllActive")
    public ResponseEntity<?> findAllActive() {

        log.info("Start Search all active exams...");
        List<Exam> contract = useCases.getAllActive();
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Found!", contract);
        log.info("Response: {}", contract.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "id") Long id) {

        log.info("Start delete exam...");
        useCases.delete(id);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Deleted!", id);
        log.info("Laboratory deleted...");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteInBlock")
    public ResponseEntity<?> deleteInBlock(@RequestBody  List<Long> idExam) {

        log.info("Start delete a list of exams...");
        List<Exam> contracts = useCases.deleteInBlock(idExam);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Deleted!", contracts);
        log.info("Response: {}", contracts.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
