package app.curs20.enitities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "studenti_to_materii")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentiToMaterie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_materie")
    private Materie materie;
}