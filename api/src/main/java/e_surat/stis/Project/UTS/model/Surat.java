package e_surat.stis.Project.UTS.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "surat")
public class Surat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jenis_surat", nullable = false)
    private String jenisSurat;

    private String judul;

    @Column(columnDefinition = "TEXT")
    private String deskripsi;

    @Column(name = "gdocs_link")
    private String gdocsLink;

    private String status;

    @Column(name = "tanggal_pengajuan")
    private Date tanggalPengajuan;

    @Column(name = "tanggal_persetujuan")
    private Date tanggalPersetujuan;

    @Column(name = "keterangan_admin")
    private String keteranganAdmin;

    @ManyToOne
    @JoinColumn(name = "mahasiswa_id", referencedColumnName = "id")
    private User mahasiswa;

    // ===== Getter & Setter =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(String jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGdocsLink() {
        return gdocsLink;
    }

    public void setGdocsLink(String gdocsLink) {
        this.gdocsLink = gdocsLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(Date tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public Date getTanggalPersetujuan() {
        return tanggalPersetujuan;
    }

    public void setTanggalPersetujuan(Date tanggalPersetujuan) {
        this.tanggalPersetujuan = tanggalPersetujuan;
    }

    public String getKeteranganAdmin() {
        return keteranganAdmin;
    }

    public void setKeteranganAdmin(String keteranganAdmin) {
        this.keteranganAdmin = keteranganAdmin;
    }

    public User getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(User mahasiswa) {
        this.mahasiswa = mahasiswa;
    }
}
