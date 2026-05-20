package main.java.bugs;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/patients")
public class HospitalApp {

    private final PatientRepository repo;

    public HospitalApp(PatientRepository repo) {
        this.repo = repo;
    }

    public static void main(String[] args) {
        SpringApplication.run(HospitalApp.class, args);
    }

    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient patient) {
        if (patient == null) {
            return ResponseEntity.badRequest().build();
        }

        Patient savedPatient = repo.save(patient);
        return ResponseEntity.ok(savedPatient);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAll() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id,
                                          @RequestBody Patient patient) {
        if (patient == null) {
            return ResponseEntity.badRequest().build();
        }

        return repo.findById(id)
                .map(existing -> {
                    existing.setName(patient.getName());
                    existing.setAge(patient.getAge());
                    existing.setDisease(patient.getDisease());

                    Patient updatedPatient = repo.save(existing);
                    return ResponseEntity.ok(updatedPatient);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repo.deleteById(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }

    @Entity
    static class Patient {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private int age;
        private String disease;

        public Patient() {
        }

        public Patient(Long id, String name, int age, String disease) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.disease = disease;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getDisease() {
            return disease;
        }

        public void setDisease(String disease) {
            this.disease = disease;
        }
    }
}

interface PatientRepository extends JpaRepository<HospitalApp.Patient, Long> {
}
