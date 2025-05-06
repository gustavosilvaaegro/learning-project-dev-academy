package com.aegro.demo.InterfaceAdpter.controller.docs;

import com.aegro.demo.Application.Dtos.RomaneioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadControllerDocs {
    @CrossOrigin
    @Operation(summary = "Upload files", description = "Upload files for analysis to Gemini")
    ResponseEntity<List<RomaneioDTO>> handleFileUpload(@RequestParam("files") List<MultipartFile> files,
                                                       @RequestParam("userEmail") String userEmail);
    @CrossOrigin
    @Operation(summary = "Get all files",
            description = "Get all files in database",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = RomaneioDTO.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No files",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    ResponseEntity<List<RomaneioDTO>> findAllFiles();

    @CrossOrigin
    @Operation(
            summary = "Get files by user email",
            description = "Get all files associated with a specific user email",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = RomaneioDTO.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request - Invalid email format",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            description = "Not Found - No files associated with the provided email",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    ResponseEntity<List<RomaneioDTO>> findeAllByUserEmail(@RequestParam("userEmail") String userEmail);
}
