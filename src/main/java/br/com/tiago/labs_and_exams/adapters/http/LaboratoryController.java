package br.com.tiago.labs_and_exams.adapters.http;


import br.com.tiago.labs_and_exams.entities.contract.Laboratory;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.response.ResponseApi;
import br.com.tiago.labs_and_exams.useCase.interfaces.LaboratoryUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/laboratory")
@Slf4j
public class LaboratoryController {

    @Autowired
    LaboratoryUseCase useCases;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody Laboratory laboratory) {

        log.info("Start Create a laboratory...");
        Laboratory contract = useCases.insert(laboratory);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.CREATED.name(), "Successfully Created!", contract);
        log.info("Response: {}", contract.toString());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/createInBlock")
    public ResponseEntity<?> createInBlock(@RequestBody List<Laboratory> laboratories) {

        log.info("Start Create a  list of laboratories...");
        List<Laboratory> contracts = useCases.insertInBlock(laboratories);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.CREATED.name(), "Successfully Created!", contracts);
        log.info("Response: {}", contracts.toString());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id,@RequestBody Laboratory laboratory) {

        log.info("Start Update a laboratory...");
        Laboratory contract = useCases.update(laboratory, id);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Updated!", contract);
        log.info("Response: {}", contract.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/putInBlock")
    public ResponseEntity<?> updateInBlock(@RequestBody  List<Laboratory> laboratories) {

        log.info("Start Update a list of laboratories...");
        List<Laboratory> contracts = useCases.updateInBlock(laboratories);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Updated!", contracts);
        log.info("Response: {}", contracts.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable(value = "name") String name) {

        log.info("Start Search ID of laboratory...");
        Laboratory contract = useCases.getByName(name);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Found!", contract);
        log.info("Response: {}", contract.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> findAll() {

        log.info("Start Search all a laboratories...");
        List<Laboratory> contract = useCases.getAll();
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Found!", contract);
        log.info("Response: {}", contract.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllActive")
    public ResponseEntity<?> findAllActive() {

        log.info("Start Search all active laboratories...");
        List<Laboratory> contract = useCases.getAllActive();
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Found!", contract);
        log.info("Response: {}", contract.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllActiveByExamName/{examName}")
    public ResponseEntity<?> findAllActiveByExamName(@PathVariable(value = "examName") String examName) {

        log.info("Start Search all active laboratories by exam name...");
        List<Laboratory> contract = useCases.getActiveLaboratoryByActiveExamName(examName);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Found!", contract);
        log.info("Response: {}", contract.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllByExamName/{examName}")
    public ResponseEntity<?> findAllByExamName(@PathVariable(value = "examName") String examName) {

        log.info("Start Search all laboratories by exam name...");
        List<Laboratory> contract = useCases.getAllLaboratoryByExamName(examName);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Found!", contract);
        log.info("Response: {}", contract.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "id") Long id) {

        log.info("Start delete laboratory...");
        useCases.delete(id);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Deleted!", id);
        log.info("Laboratory deleted...");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/DeleteInBlock")
    public ResponseEntity<?> deleteInBlock(@RequestBody  List<Long> idLaboratory) {

        log.info("Start delete a list of laboratories...");
        List<Laboratory> contracts = useCases.deleteInBlock(idLaboratory);
        ResponseApi response = ResponseApi.setResponseSuccess(HttpStatus.OK.name(), "Successfully Deleted!", contracts);
        log.info("Response: {}", contracts.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
