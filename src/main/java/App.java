import Repository.EmployeeRepository;
import controller.EmployeeController;
import database.EmployeeDb;
import mapper.EmployeeMapper;
import service.EmployeeService;
import service.impl.EmployeeServiceImpl;
import view.EmployeeView;

public class App {
    public static void main(String[] args) {


        // the way to create object controller : we need view and service

        EmployeeView view = new EmployeeView();

        EmployeeMapper mapper = new EmployeeMapper();
        EmployeeDb db = new EmployeeDb();
        EmployeeRepository repository = new EmployeeRepository(db);
        EmployeeService service = new EmployeeServiceImpl(repository, mapper);
        EmployeeController controller = new EmployeeController(view, service);

        controller.start();

    }
}
