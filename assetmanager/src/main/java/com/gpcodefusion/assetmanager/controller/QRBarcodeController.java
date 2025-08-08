package com.gpcodefusion.assetmanager.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.oned.Code39Writer;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/generator")
public class QRBarcodeController {

    private final String QR_PATH = "GeneratedQRCode.png";
    private final String BARCODE_PATH = "GeneratedBarcode.png";

    // ✅ Generate QR Code
    @PostMapping("/generate-qr")
    public ResponseEntity<String> generateQR(@RequestParam String content) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300);

            Path path = new File(QR_PATH).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            return ResponseEntity.ok("/api/generator/view-qr");
        } catch (WriterException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("QR Code generation failed: " + e.getMessage());
        }
    }

    // ✅ Generate Barcode
    @PostMapping("/generate-barcode")
    public ResponseEntity<String> generateBarcode(@RequestParam String content) {
        try {
            Code39Writer code39Writer = new Code39Writer();
            BitMatrix bitMatrix = code39Writer.encode(content, BarcodeFormat.CODE_39, 300, 100);

            Path path = new File(BARCODE_PATH).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            return ResponseEntity.ok("/api/generator/view-barcode");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Barcode generation failed: " + e.getMessage());
        }
    }

    // ✅ View QR Code
    @GetMapping("/view-qr")
    public void viewQR(HttpServletResponse response) throws IOException {
        File file = new File(QR_PATH);
        if (file.exists()) {
            response.setContentType("image/png");
            Files.copy(file.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "QR code not found");
        }
    }

    // ✅ View Barcode
    @GetMapping("/view-barcode")
    public void viewBarcode(HttpServletResponse response) throws IOException {
        File file = new File(BARCODE_PATH);
        if (file.exists()) {
            response.setContentType("image/png");
            Files.copy(file.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Barcode not found");
        }
    }

    // ✅ Download QR Code
    @GetMapping("/download-qr")
    public void downloadQR(HttpServletResponse response) throws IOException {
        File file = new File(QR_PATH);
        if (file.exists()) {
            response.setContentType("image/png");
            response.setHeader("Content-Disposition", "attachment; filename=GeneratedQRCode.png");
            Files.copy(file.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "QR code not found for download");
        }
    }

    // ✅ Download Barcode
    @GetMapping("/download-barcode")
    public void downloadBarcode(HttpServletResponse response) throws IOException {
        File file = new File(BARCODE_PATH);
        if (file.exists()) {
            response.setContentType("image/png");
            response.setHeader("Content-Disposition", "attachment; filename=GeneratedBarcode.png");
            Files.copy(file.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Barcode not found for download");
        }
    }
}
