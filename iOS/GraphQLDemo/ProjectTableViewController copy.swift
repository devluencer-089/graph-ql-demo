import UIKit

class ProjectTableViewController: UITableViewController {
    var projects: [ProjectsQuery.Data.Employee.Project] = [] {
        didSet {
            tableView.reloadData()
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        apollo.fetch(query: ProjectsQuery()) { [weak self] (result, error) in
            if let error = error { print(error) }
            
            self?.projects = result?.data?.employees.compactMap { $0.project } ?? []
        }
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return projects.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let project = projects[indexPath.row]
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "ProjectCell", for: indexPath)
        
        cell.textLabel?.text = "\(project.id). CST-Lead: \(project.cstLead?.description ?? "?")"
        cell.detailTextLabel?.text = "\(project.staff?.count.description ?? "?") staffed"
        cell.imageView?.image = #imageLiteral(resourceName: "Derelict House")

        return cell
    }
}

extension ProjectsQuery.Data.Employee.Project.CstLead: CustomStringConvertible {
    public var description: String {
        return "\(firstName) \(lastName) (\(age))"
    }
}
