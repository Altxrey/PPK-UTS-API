package e_surat.stis.Project.UTS.controller;

import e_surat.stis.Project.UTS.model.Surat;
import e_surat.stis.Project.UTS.service.SuratService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surat")
@SecurityRequirement(name = "Bearer Authentication") // Swagger tahu butuh JWT
@Tag(name = "Surat", description = "Manajemen data surat mahasiswa dan admin")
public class SuratController {

    @Autowired
    private SuratService suratService;

    // ===============================
    // CREATE SURAT
    // ===============================
    @Operation(
            summary = "Tambah surat baru",
            description = "Mahasiswa mengajukan surat baru ke sistem e-Surat."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Surat berhasil dibuat",
                    content = @Content(schema = @Schema(implementation = Surat.class))),
            @ApiResponse(responseCode = "400", description = "Data surat tidak valid", content = @Content),
            @ApiResponse(responseCode = "403", description = "Tidak memiliki izin (JWT salah/tidak ada)", content = @Content)
    })
    @PostMapping("/create")
    public Surat createSurat(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objek surat yang akan ditambahkan",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Surat.class))
            )
            @RequestBody Surat surat) {
        return suratService.createSurat(surat);
    }

    // ===============================
    // GET SEMUA SURAT
    // ===============================
    @Operation(summary = "Ambil semua surat", description = "Menampilkan daftar semua surat yang telah diajukan.")
    @ApiResponse(responseCode = "200", description = "Berhasil mengambil daftar surat")
    @GetMapping("/all")
    public List<Surat> getAll() {
        return suratService.getAll();
    }

    // ===============================
    // GET SURAT BY ID
    // ===============================
    @Operation(summary = "Ambil surat berdasarkan ID", description = "Menampilkan detail surat berdasarkan ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Surat ditemukan"),
            @ApiResponse(responseCode = "404", description = "Surat tidak ditemukan")
    })
    @GetMapping("/{id}")
    public Surat getById(
            @Parameter(description = "ID surat yang ingin dicari", example = "1")
            @PathVariable Long id) {
        return suratService.getById(id);
    }

    // ===============================
    // APPROVE SURAT (ADMIN/BAU)
    // ===============================
    @Operation(
            summary = "Setujui surat",
            description = "Digunakan oleh admin/BAU untuk menyetujui surat mahasiswa."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Surat berhasil disetujui"),
            @ApiResponse(responseCode = "404", description = "Surat tidak ditemukan"),
            @ApiResponse(responseCode = "403", description = "Tidak memiliki izin")
    })
    @PutMapping("/{id}/approve")
    public Surat approveSurat(
            @Parameter(description = "ID surat yang akan disetujui", example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Keterangan admin (opsional)",
                    required = false,
                    content = @Content(schema = @Schema(example = "Surat disetujui oleh BAU."))
            )
            @RequestBody(required = false) String keteranganAdmin) {

        if (keteranganAdmin == null || keteranganAdmin.isEmpty()) {
            keteranganAdmin = "Surat disetujui oleh BAU.";
        }
        return suratService.approveSurat(id, keteranganAdmin);
    }

    // ===============================
    // REJECT SURAT (ADMIN/BAU)
    // ===============================
    @Operation(
            summary = "Tolak surat",
            description = "Digunakan oleh admin/BAU untuk menolak surat mahasiswa."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Surat berhasil ditolak"),
            @ApiResponse(responseCode = "404", description = "Surat tidak ditemukan"),
            @ApiResponse(responseCode = "403", description = "Tidak memiliki izin")
    })
    @PutMapping("/{id}/reject")
    public Surat rejectSurat(
            @Parameter(description = "ID surat yang akan ditolak", example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Keterangan admin (opsional)",
                    required = false,
                    content = @Content(schema = @Schema(example = "Surat ditolak karena data tidak lengkap."))
            )
            @RequestBody(required = false) String keteranganAdmin) {

        if (keteranganAdmin == null || keteranganAdmin.isEmpty()) {
            keteranganAdmin = "Surat ditolak oleh BAU.";
        }
        return suratService.rejectSurat(id, keteranganAdmin);
    }
}
