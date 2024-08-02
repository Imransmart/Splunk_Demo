import org.example.entity.Department;
import org.example.repository.DepartmentRepository;
import org.example.service.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
       // MockitoAnnotations.initMocks(this);
        System.out.println("I am executed successfully..");
    }

    @Test
    public void TestCreateDepartment(){

        Department department=new Department(1,"CSE","Gudur","504");

        when(departmentRepository.save(department)).thenReturn(department);

        Department department1=departmentService.createDepartment(department);

        assertEquals(department1,department);
        verify(departmentRepository).save(department);

    }

    @Test
    public void TestFetchDepartmentList(){
        when(departmentRepository.findAll()).thenReturn(Arrays.asList(new Department(),new Department()));

        List<Department> result=departmentService.fetchDeparmentList();

        assertEquals(2,result.size());
        verify(departmentRepository,times(1)).findAll();

    }

    @Test
    public void TestDeleteDepartmentById(){
        doNothing().when(departmentRepository).deleteById(anyInt());
        departmentService.deleteDepartmentById(1);
        verify(departmentRepository,times(1)).deleteById(1);

    }


}
