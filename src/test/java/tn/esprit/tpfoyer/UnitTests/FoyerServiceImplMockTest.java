package tn.esprit.tpfoyer.UnitTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.NoSuchElementException;
import java.util.Optional;

public class FoyerServiceImplMockTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveFoyer_FoyerExists_ReturnsFoyer() {
        // Arrange
        Long foyerId = 1L;
        Foyer mockFoyer = new Foyer();
        mockFoyer.setIdFoyer(foyerId);
        mockFoyer.setNomFoyer("Test Foyer");
        mockFoyer.setCapaciteFoyer(100);
        when(foyerRepository.findById(foyerId)).thenReturn(Optional.of(mockFoyer));

        // Act
        Foyer foyer = foyerService.retrieveFoyer(foyerId);

        // Assert
        assertNotNull(foyer);
        assertEquals("Test Foyer", foyer.getNomFoyer());
        assertEquals(100, foyer.getCapaciteFoyer());
        verify(foyerRepository, times(1)).findById(foyerId);
    }

    @Test
    void retrieveFoyer_FoyerDoesNotExist_ThrowsException() {
        // Arrange
        Long foyerId = 1L;
        when(foyerRepository.findById(foyerId)).thenReturn(Optional.empty());

        // Act & Assert
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> foyerService.retrieveFoyer(foyerId));
        verify(foyerRepository, times(1)).findById(foyerId);
    }
}
