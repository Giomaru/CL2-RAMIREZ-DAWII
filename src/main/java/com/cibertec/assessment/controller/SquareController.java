package com.cibertec.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.assessment.beans.SquareBean;
import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.service.SquareService;

@RestController
@RequestMapping("/squares")
public class SquareController {
	
	  @Autowired
	    private SquareService squareService;

	    @PostMapping
	    public void createSquare(@RequestBody Square square) {
	        squareService.create(square);
	    }

	    @PostMapping("/batch")
	    public void createSquares(@RequestBody List<Square> squares) {
	        squareService.create(squares);
	    }

	    @GetMapping
	    public List<SquareBean> listSquares() {
	        return squareService.list();
	    }

}
