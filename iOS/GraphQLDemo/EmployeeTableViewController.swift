import UIKit

class EmployeeTableViewController: UITableViewController {
    private var employees: [EmployeesQuery.Data.Employee] = [] {
        didSet {
            tableView.reloadData()
        }
    }
    
    private lazy var watchedQuery = apollo.watch(query: EmployeesQuery()) { [weak self] (result, error) in
        if let error = error { print(error) }
        
        self?.employees = result?.data?.employees ?? []
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        watchedQuery.refetch()
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return employees.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let employee = employees[indexPath.row]
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "EmployeeCell", for: indexPath)
        
        cell.textLabel?.text = "\(employee.firstName) \(employee.lastName) (\(employee.age))"
        cell.imageView?.image = {
            switch employee.gender {
            case .male: return #imageLiteral(resourceName: "Male Technologist")
            case .female: return #imageLiteral(resourceName: "Female Technologist")
            default: return nil
            }
        }()

        return cell
    }
}
