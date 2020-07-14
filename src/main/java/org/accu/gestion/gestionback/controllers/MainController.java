package org.accu.gestion.gestionback.controllers;

import org.accu.gestion.gestionback.model.asociado.Asociado;
import org.accu.gestion.gestionback.model.hospital.Hospital;
import org.accu.gestion.gestionback.service.AsociadoService;
import org.accu.gestion.gestionback.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class MainController {

    private AsociadoService asociadoService;
    private HospitalService hospitalService;


    @Autowired
    public MainController(AsociadoService asociadoService, HospitalService hospitalService) {
        this.asociadoService = asociadoService;
        this.hospitalService = hospitalService;
    }

    @RequestMapping(value = "/asociados", method = RequestMethod.GET)
    public List<Asociado> findAllAsociados() { return asociadoService.findAll(); }


    @RequestMapping(value ="/asociado/{idAsociado}")
    public Asociado findAsociadoById(@PathVariable("idAsociado") String idAsociado) throws Exception {
        return asociadoService.findAsociadoById(idAsociado);
    }

    @RequestMapping(value = "/hospitales")
    public List<Hospital> findAllHospitales() {
        return hospitalService.findAll();
    }

    @RequestMapping(value = "/hospital/{idHospital}")
    public Hospital findHospitalById(@PathVariable("idHospital") String idHospital) throws Exception {
        return hospitalService.findHospitalById(idHospital);
    }

    @PostMapping(value = "/asociados/newAsociado")
    public Asociado newAsociado(@RequestBody Asociado asociado){
        return asociadoService.saveAsociado(asociado);
    }

    @PutMapping("/asociados/{idAsociado}")
    public ResponseEntity<Asociado> updateAsociado (@PathVariable(value = "idAsociado") String idAsociado,
                                                    @RequestBody Asociado asociadoDetails){
        final Asociado asociadoActualizado = asociadoService.saveAsociado(asociadoDetails);
        return ResponseEntity.ok(asociadoActualizado);
    }

    @DeleteMapping("/asociados/{idAsociado}/remove")
    public Map<String, Boolean> deleteAsociado (@PathVariable(value="idAsociado") String idAsociado) throws Exception{
        return asociadoService.deleteAsociado(idAsociado);
    }
}
