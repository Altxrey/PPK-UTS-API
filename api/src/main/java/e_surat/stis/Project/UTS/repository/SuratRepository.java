package e_surat.stis.Project.UTS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import e_surat.stis.Project.UTS.model.Surat;

@Repository
public interface SuratRepository extends JpaRepository<Surat, Long> {
    // Kamu bisa menambahkan query custom di sini kalau mau
}
