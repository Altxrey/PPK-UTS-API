package e_surat.stis.Project.UTS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import e_surat.stis.Project.UTS.model.Surat;
import e_surat.stis.Project.UTS.repository.SuratRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class SuratService {

    @Autowired
    private SuratRepository suratRepository;

    // CREATE surat baru
    public Surat createSurat(Surat surat) {
        surat.setStatus("DIPROSES");
        surat.setTanggalPengajuan(Date.valueOf(LocalDate.now())); // konversi ke java.sql.Date
        surat.setTanggalPersetujuan(null);
        surat.setKeteranganAdmin(null);
        return suratRepository.save(surat);
    }

    // GET semua surat
    public List<Surat> getAll() {
        return suratRepository.findAll();
    }

    // SETUJU surat (oleh admin/BAU)
    public Surat approveSurat(Long id, String keteranganAdmin) {
        Surat surat = suratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Surat tidak ditemukan"));
        surat.setStatus("DISETUJUI");
        surat.setTanggalPersetujuan(Date.valueOf(LocalDate.now()));
        surat.setKeteranganAdmin(keteranganAdmin);
        return suratRepository.save(surat);
    }

    // TOLAK surat (oleh admin/BAU)
    public Surat rejectSurat(Long id, String keteranganAdmin) {
        Surat surat = suratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Surat tidak ditemukan"));
        surat.setStatus("DITOLAK");
        surat.setTanggalPersetujuan(Date.valueOf(LocalDate.now()));
        surat.setKeteranganAdmin(keteranganAdmin);
        return suratRepository.save(surat);
    }

    // GET surat berdasarkan id
    public Surat getById(Long id) {
        return suratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Surat tidak ditemukan"));
    }
}
