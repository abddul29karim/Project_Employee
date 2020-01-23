package com.btpn.employee.Service;

import com.btpn.employee.Dao.DaoResponse;
import com.btpn.employee.Dao.E_Dao;
import com.btpn.employee.Dao.E_DaoImp;
import com.btpn.employee.Entity.Employee_Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class E_ServiceImp implements E_Service {
    @Autowired
    private E_Dao e_dao;

    @Override
    public ResponseEntity<DaoResponse> save(Employee_Db employee_db) {
        DaoResponse response = new DaoResponse();
        String emailreg = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String nik = "[0-9]{8}";
        String name = "[a-zA-Z ]+$";

        if (employee_db.getNik() == null ||
                employee_db.getName() == null ||
                employee_db.getEmail() == null ||
                employee_db.getAddress() == null ||
                !employee_db.getEmail().matches(emailreg) ||
                !employee_db.getNik().matches(nik) ||
                !employee_db.getName().matches(name)) {

                response.setCode(400);
                response.setStatus("Bad request");
                response.setMessagae("data tidak boleh kosong / format email");
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(response);

        }
        Employee_Db empemail = e_dao.findByEmail(employee_db.getEmail());
        if (empemail != null) {
            response.setCode(400);
            response.setStatus("Bad request");
            response.setMessagae("data tidak boleh kosong / format email");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);

        }


        e_dao.save(employee_db);
        response.setData(employee_db);
        response.setCode(201);
        response.setStatus("Created");
        response.setMessagae("Data Berhasil di input");
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @Override
    public ResponseEntity<DaoResponse> getEmp() {
        List<Employee_Db> emp = e_dao.getEmp();
        DaoResponse response = new DaoResponse();
        if (emp == null) {
            response.setCode(400);
            response.setStatus("Bad request");
            response.setMessagae("data tidak boleh kosong / format email");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } else {
            response.setData(emp);
            response.setCode(200);
            response.setStatus("data karyawan");
            response.setMessagae("data");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
    }

    @Override
    public ResponseEntity<DaoResponse> findById(Integer emp_id) {
        DaoResponse response = new DaoResponse();
        Employee_Db employee_db = e_dao.findById(emp_id);
        if (employee_db == null) {
            response.setCode(400);
            response.setStatus("Bad request");
            response.setMessagae("data tidak boleh kosong");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } else {
            response.setData(employee_db);
            response.setCode(200);
            response.setStatus("data ditemukan");
            response.setMessagae("data ada");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
    }

    @Override
    public ResponseEntity<DaoResponse> update(Employee_Db employee, Integer emp_id) {
        DaoResponse response = new DaoResponse();
        Employee_Db employee_db = e_dao.findById(emp_id);
        if (employee_db == null) {
            response.setCode(400);
            response.setStatus("Bad request");
            response.setMessagae("data tidak boleh kosong");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } else {
            Employee_Db data = e_dao.update(employee_db, employee_db.getEmp_id());
            response.setData(data);
            response.setCode(200);
            response.setStatus("data ditemukan");
            response.setMessagae("data berhasil");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
    }

    @Override
    public ResponseEntity<DaoResponse> delemp(Integer emp_id) {
        DaoResponse response = new DaoResponse();
        Employee_Db employee_db = e_dao.findById(emp_id);
        if (employee_db == null) {
            response.setCode(404);
            response.setStatus("data kosong");
            response.setMessagae("data tidak ditemukan");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } else {
            e_dao.delemp(emp_id);
            response.setCode(200);
            response.setStatus("data ditemukan");
            response.setMessagae("data berhasil");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
    }

    @Override
    public ResponseEntity<DaoResponse> findByNik(String nik) {
        DaoResponse response = new DaoResponse();
        Employee_Db employee_db = e_dao.findByNik(nik);
        if (employee_db == null) {
            response.setCode(400);
            response.setStatus("Bad request");
            response.setMessagae("data tidak boleh kosong");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } else {
            response.setData(employee_db);
            response.setCode(200);
            response.setStatus("data ditemukan");
            response.setMessagae("data ada");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
    }

    @Override
    public ResponseEntity<DaoResponse> findByEmail(String email) {
        DaoResponse response = new DaoResponse();
        Employee_Db employee_db = e_dao.findByEmail(email);
        if (employee_db == null) {
            response.setCode(400);
            response.setStatus("Bad request");
            response.setMessagae("data tidak boleh kosong");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } else {
            response.setData(employee_db);
            response.setCode(200);
            response.setStatus("data ditemukan");
            response.setMessagae("data ada");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
    }

    @Override
    public ResponseEntity<DaoResponse> findByName(String name) {
        DaoResponse response = new DaoResponse();
        Employee_Db employee_db = e_dao.findByName(name);
        if (employee_db == null) {
            response.setCode(400);
            response.setStatus("Bad request");
            response.setMessagae("data tidak boleh kosong");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } else {
            response.setData(employee_db);
            response.setCode(200);
            response.setStatus("data ditemukan");
            response.setMessagae("data ada");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
    }
}