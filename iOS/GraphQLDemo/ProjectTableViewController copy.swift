import UIKit

class ProjectTableViewController: UITableViewController {
    var projects: [ProjectsQuery.Data.Employee.Project] = [] {
        didSet {
            tableView.reloadData()
        }
    }
    
    private lazy var watchedQuery = apollo.watch(query: ProjectsQuery()) { [weak self] (result, error) in
        if let error = error { print(error) }
        
        self?.projects = result?.data?.employees.compactMap { $0.project } ?? []
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        watchedQuery.refetch()
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return projects.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let project = projects[indexPath.row]
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "ProjectCell", for: indexPath)
        
        cell.textLabel?.text = "\(project.id). CST-Lead: \(project.cstLead?.description ?? "?")"
        cell.detailTextLabel?.text = "\(project.staff?.count.description ?? "?") staffed"
        cell.detailTextLabel?.text = {
            guard let staff = project.staff else { return nil }
            
            let femaleCount = staff.filter { $0.gender == .female }.count
            let maleCount = staff.filter { $0.gender == .male}.count
            let remainingCount = staff.count - maleCount - femaleCount
            return "\(String(repeating: "🚣‍♀️", count: femaleCount))\(String(repeating: "🚣‍♂️", count: maleCount))\(String(repeating: "🛶", count: remainingCount))"
        }()
        cell.imageView?.image = #imageLiteral(resourceName: "Derelict House")

        return cell
    }
}

extension ProjectsQuery.Data.Employee.Project.CstLead: CustomStringConvertible {
    public var description: String {
        return "\(firstName) \(lastName)"
    }
}
