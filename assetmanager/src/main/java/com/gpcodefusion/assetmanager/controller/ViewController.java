package com.gpcodefusion.assetmanager.controller;

import com.gpcodefusion.assetmanager.model.AssetMappingDetails;
import com.gpcodefusion.assetmanager.repository.AssetMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ViewController {

    @Autowired
    private AssetMappingRepository assetMappingRepository;

    @GetMapping("/viewBarcode")
    public String viewBarcode(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<AssetMappingDetails> assetPage = assetMappingRepository.findAll(
                PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("assetPage", assetPage);

        int totalPages = assetPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "viewBarcode";
    }
}