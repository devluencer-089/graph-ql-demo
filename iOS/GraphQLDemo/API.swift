//  This file was automatically generated and should not be edited.

import Apollo

public enum Gender: RawRepresentable, Equatable, Apollo.JSONDecodable, Apollo.JSONEncodable {
  public typealias RawValue = String
  case female
  case male
  /// Auto generated constant for unknown enum values
  case __unknown(RawValue)

  public init?(rawValue: RawValue) {
    switch rawValue {
      case "FEMALE": self = .female
      case "MALE": self = .male
      default: self = .__unknown(rawValue)
    }
  }

  public var rawValue: RawValue {
    switch self {
      case .female: return "FEMALE"
      case .male: return "MALE"
      case .__unknown(let value): return value
    }
  }

  public static func == (lhs: Gender, rhs: Gender) -> Bool {
    switch (lhs, rhs) {
      case (.female, .female): return true
      case (.male, .male): return true
      case (.__unknown(let lhsValue), .__unknown(let rhsValue)): return lhsValue == rhsValue
      default: return false
    }
  }
}

public final class EmployeeQuery: GraphQLQuery {
  public static let operationString =
    "query Employee($id: ID!) {\n  employee(id: $id) {\n    __typename\n    ...EmployeeDetails\n  }\n}"

  public static var requestString: String { return operationString.appending(EmployeeDetails.fragmentString) }

  public var id: GraphQLID

  public init(id: GraphQLID) {
    self.id = id
  }

  public var variables: GraphQLMap? {
    return ["id": id]
  }

  public struct Data: GraphQLSelectionSet {
    public static let possibleTypes = ["Query"]

    public static let selections: [GraphQLSelection] = [
      GraphQLField("employee", arguments: ["id": GraphQLVariable("id")], type: .object(Employee.selections)),
    ]

    public var snapshot: Snapshot

    public init(snapshot: Snapshot) {
      self.snapshot = snapshot
    }

    public init(employee: Employee? = nil) {
      self.init(snapshot: ["__typename": "Query", "employee": employee.flatMap { (value: Employee) -> Snapshot in value.snapshot }])
    }

    /// employee by id
    public var employee: Employee? {
      get {
        return (snapshot["employee"] as? Snapshot).flatMap { Employee(snapshot: $0) }
      }
      set {
        snapshot.updateValue(newValue?.snapshot, forKey: "employee")
      }
    }

    public struct Employee: GraphQLSelectionSet {
      public static let possibleTypes = ["Employee"]

      public static let selections: [GraphQLSelection] = [
        GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
        GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
        GraphQLField("id", type: .nonNull(.scalar(GraphQLID.self))),
        GraphQLField("firstName", type: .nonNull(.scalar(String.self))),
        GraphQLField("lastName", type: .nonNull(.scalar(String.self))),
        GraphQLField("age", type: .nonNull(.scalar(Int.self))),
        GraphQLField("gender", type: .nonNull(.scalar(Gender.self))),
        GraphQLField("projectId", type: .scalar(GraphQLID.self)),
        GraphQLField("project", type: .object(Project.selections)),
      ]

      public var snapshot: Snapshot

      public init(snapshot: Snapshot) {
        self.snapshot = snapshot
      }

      public init(id: GraphQLID, firstName: String, lastName: String, age: Int, gender: Gender, projectId: GraphQLID? = nil, project: Project? = nil) {
        self.init(snapshot: ["__typename": "Employee", "id": id, "firstName": firstName, "lastName": lastName, "age": age, "gender": gender, "projectId": projectId, "project": project.flatMap { (value: Project) -> Snapshot in value.snapshot }])
      }

      public var __typename: String {
        get {
          return snapshot["__typename"]! as! String
        }
        set {
          snapshot.updateValue(newValue, forKey: "__typename")
        }
      }

      public var id: GraphQLID {
        get {
          return snapshot["id"]! as! GraphQLID
        }
        set {
          snapshot.updateValue(newValue, forKey: "id")
        }
      }

      public var firstName: String {
        get {
          return snapshot["firstName"]! as! String
        }
        set {
          snapshot.updateValue(newValue, forKey: "firstName")
        }
      }

      public var lastName: String {
        get {
          return snapshot["lastName"]! as! String
        }
        set {
          snapshot.updateValue(newValue, forKey: "lastName")
        }
      }

      public var age: Int {
        get {
          return snapshot["age"]! as! Int
        }
        set {
          snapshot.updateValue(newValue, forKey: "age")
        }
      }

      public var gender: Gender {
        get {
          return snapshot["gender"]! as! Gender
        }
        set {
          snapshot.updateValue(newValue, forKey: "gender")
        }
      }

      public var projectId: GraphQLID? {
        get {
          return snapshot["projectId"] as? GraphQLID
        }
        set {
          snapshot.updateValue(newValue, forKey: "projectId")
        }
      }

      /// the project this employee is currently assigned to
      public var project: Project? {
        get {
          return (snapshot["project"] as? Snapshot).flatMap { Project(snapshot: $0) }
        }
        set {
          snapshot.updateValue(newValue?.snapshot, forKey: "project")
        }
      }

      public var fragments: Fragments {
        get {
          return Fragments(snapshot: snapshot)
        }
        set {
          snapshot += newValue.snapshot
        }
      }

      public struct Fragments {
        public var snapshot: Snapshot

        public var employeeDetails: EmployeeDetails {
          get {
            return EmployeeDetails(snapshot: snapshot)
          }
          set {
            snapshot += newValue.snapshot
          }
        }
      }

      public struct Project: GraphQLSelectionSet {
        public static let possibleTypes = ["Project"]

        public static let selections: [GraphQLSelection] = [
          GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
          GraphQLField("id", type: .nonNull(.scalar(GraphQLID.self))),
        ]

        public var snapshot: Snapshot

        public init(snapshot: Snapshot) {
          self.snapshot = snapshot
        }

        public init(id: GraphQLID) {
          self.init(snapshot: ["__typename": "Project", "id": id])
        }

        public var __typename: String {
          get {
            return snapshot["__typename"]! as! String
          }
          set {
            snapshot.updateValue(newValue, forKey: "__typename")
          }
        }

        public var id: GraphQLID {
          get {
            return snapshot["id"]! as! GraphQLID
          }
          set {
            snapshot.updateValue(newValue, forKey: "id")
          }
        }
      }
    }
  }
}

public final class EmployeesQuery: GraphQLQuery {
  public static let operationString =
    "query Employees {\n  employees {\n    __typename\n    ...EmployeeDetails\n  }\n}"

  public static var requestString: String { return operationString.appending(EmployeeDetails.fragmentString) }

  public init() {
  }

  public struct Data: GraphQLSelectionSet {
    public static let possibleTypes = ["Query"]

    public static let selections: [GraphQLSelection] = [
      GraphQLField("employees", type: .nonNull(.list(.nonNull(.object(Employee.selections))))),
    ]

    public var snapshot: Snapshot

    public init(snapshot: Snapshot) {
      self.snapshot = snapshot
    }

    public init(employees: [Employee]) {
      self.init(snapshot: ["__typename": "Query", "employees": employees.map { (value: Employee) -> Snapshot in value.snapshot }])
    }

    /// all employees
    public var employees: [Employee] {
      get {
        return (snapshot["employees"] as! [Snapshot]).map { (value: Snapshot) -> Employee in Employee(snapshot: value) }
      }
      set {
        snapshot.updateValue(newValue.map { (value: Employee) -> Snapshot in value.snapshot }, forKey: "employees")
      }
    }

    public struct Employee: GraphQLSelectionSet {
      public static let possibleTypes = ["Employee"]

      public static let selections: [GraphQLSelection] = [
        GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
        GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
        GraphQLField("id", type: .nonNull(.scalar(GraphQLID.self))),
        GraphQLField("firstName", type: .nonNull(.scalar(String.self))),
        GraphQLField("lastName", type: .nonNull(.scalar(String.self))),
        GraphQLField("age", type: .nonNull(.scalar(Int.self))),
        GraphQLField("gender", type: .nonNull(.scalar(Gender.self))),
        GraphQLField("projectId", type: .scalar(GraphQLID.self)),
        GraphQLField("project", type: .object(Project.selections)),
      ]

      public var snapshot: Snapshot

      public init(snapshot: Snapshot) {
        self.snapshot = snapshot
      }

      public init(id: GraphQLID, firstName: String, lastName: String, age: Int, gender: Gender, projectId: GraphQLID? = nil, project: Project? = nil) {
        self.init(snapshot: ["__typename": "Employee", "id": id, "firstName": firstName, "lastName": lastName, "age": age, "gender": gender, "projectId": projectId, "project": project.flatMap { (value: Project) -> Snapshot in value.snapshot }])
      }

      public var __typename: String {
        get {
          return snapshot["__typename"]! as! String
        }
        set {
          snapshot.updateValue(newValue, forKey: "__typename")
        }
      }

      public var id: GraphQLID {
        get {
          return snapshot["id"]! as! GraphQLID
        }
        set {
          snapshot.updateValue(newValue, forKey: "id")
        }
      }

      public var firstName: String {
        get {
          return snapshot["firstName"]! as! String
        }
        set {
          snapshot.updateValue(newValue, forKey: "firstName")
        }
      }

      public var lastName: String {
        get {
          return snapshot["lastName"]! as! String
        }
        set {
          snapshot.updateValue(newValue, forKey: "lastName")
        }
      }

      public var age: Int {
        get {
          return snapshot["age"]! as! Int
        }
        set {
          snapshot.updateValue(newValue, forKey: "age")
        }
      }

      public var gender: Gender {
        get {
          return snapshot["gender"]! as! Gender
        }
        set {
          snapshot.updateValue(newValue, forKey: "gender")
        }
      }

      public var projectId: GraphQLID? {
        get {
          return snapshot["projectId"] as? GraphQLID
        }
        set {
          snapshot.updateValue(newValue, forKey: "projectId")
        }
      }

      /// the project this employee is currently assigned to
      public var project: Project? {
        get {
          return (snapshot["project"] as? Snapshot).flatMap { Project(snapshot: $0) }
        }
        set {
          snapshot.updateValue(newValue?.snapshot, forKey: "project")
        }
      }

      public var fragments: Fragments {
        get {
          return Fragments(snapshot: snapshot)
        }
        set {
          snapshot += newValue.snapshot
        }
      }

      public struct Fragments {
        public var snapshot: Snapshot

        public var employeeDetails: EmployeeDetails {
          get {
            return EmployeeDetails(snapshot: snapshot)
          }
          set {
            snapshot += newValue.snapshot
          }
        }
      }

      public struct Project: GraphQLSelectionSet {
        public static let possibleTypes = ["Project"]

        public static let selections: [GraphQLSelection] = [
          GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
          GraphQLField("id", type: .nonNull(.scalar(GraphQLID.self))),
        ]

        public var snapshot: Snapshot

        public init(snapshot: Snapshot) {
          self.snapshot = snapshot
        }

        public init(id: GraphQLID) {
          self.init(snapshot: ["__typename": "Project", "id": id])
        }

        public var __typename: String {
          get {
            return snapshot["__typename"]! as! String
          }
          set {
            snapshot.updateValue(newValue, forKey: "__typename")
          }
        }

        public var id: GraphQLID {
          get {
            return snapshot["id"]! as! GraphQLID
          }
          set {
            snapshot.updateValue(newValue, forKey: "id")
          }
        }
      }
    }
  }
}

public struct EmployeeDetails: GraphQLFragment {
  public static let fragmentString =
    "fragment EmployeeDetails on Employee {\n  __typename\n  id\n  firstName\n  lastName\n  age\n  gender\n  projectId\n  project {\n    __typename\n    id\n  }\n}"

  public static let possibleTypes = ["Employee"]

  public static let selections: [GraphQLSelection] = [
    GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
    GraphQLField("id", type: .nonNull(.scalar(GraphQLID.self))),
    GraphQLField("firstName", type: .nonNull(.scalar(String.self))),
    GraphQLField("lastName", type: .nonNull(.scalar(String.self))),
    GraphQLField("age", type: .nonNull(.scalar(Int.self))),
    GraphQLField("gender", type: .nonNull(.scalar(Gender.self))),
    GraphQLField("projectId", type: .scalar(GraphQLID.self)),
    GraphQLField("project", type: .object(Project.selections)),
  ]

  public var snapshot: Snapshot

  public init(snapshot: Snapshot) {
    self.snapshot = snapshot
  }

  public init(id: GraphQLID, firstName: String, lastName: String, age: Int, gender: Gender, projectId: GraphQLID? = nil, project: Project? = nil) {
    self.init(snapshot: ["__typename": "Employee", "id": id, "firstName": firstName, "lastName": lastName, "age": age, "gender": gender, "projectId": projectId, "project": project.flatMap { (value: Project) -> Snapshot in value.snapshot }])
  }

  public var __typename: String {
    get {
      return snapshot["__typename"]! as! String
    }
    set {
      snapshot.updateValue(newValue, forKey: "__typename")
    }
  }

  public var id: GraphQLID {
    get {
      return snapshot["id"]! as! GraphQLID
    }
    set {
      snapshot.updateValue(newValue, forKey: "id")
    }
  }

  public var firstName: String {
    get {
      return snapshot["firstName"]! as! String
    }
    set {
      snapshot.updateValue(newValue, forKey: "firstName")
    }
  }

  public var lastName: String {
    get {
      return snapshot["lastName"]! as! String
    }
    set {
      snapshot.updateValue(newValue, forKey: "lastName")
    }
  }

  public var age: Int {
    get {
      return snapshot["age"]! as! Int
    }
    set {
      snapshot.updateValue(newValue, forKey: "age")
    }
  }

  public var gender: Gender {
    get {
      return snapshot["gender"]! as! Gender
    }
    set {
      snapshot.updateValue(newValue, forKey: "gender")
    }
  }

  public var projectId: GraphQLID? {
    get {
      return snapshot["projectId"] as? GraphQLID
    }
    set {
      snapshot.updateValue(newValue, forKey: "projectId")
    }
  }

  /// the project this employee is currently assigned to
  public var project: Project? {
    get {
      return (snapshot["project"] as? Snapshot).flatMap { Project(snapshot: $0) }
    }
    set {
      snapshot.updateValue(newValue?.snapshot, forKey: "project")
    }
  }

  public struct Project: GraphQLSelectionSet {
    public static let possibleTypes = ["Project"]

    public static let selections: [GraphQLSelection] = [
      GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
      GraphQLField("id", type: .nonNull(.scalar(GraphQLID.self))),
    ]

    public var snapshot: Snapshot

    public init(snapshot: Snapshot) {
      self.snapshot = snapshot
    }

    public init(id: GraphQLID) {
      self.init(snapshot: ["__typename": "Project", "id": id])
    }

    public var __typename: String {
      get {
        return snapshot["__typename"]! as! String
      }
      set {
        snapshot.updateValue(newValue, forKey: "__typename")
      }
    }

    public var id: GraphQLID {
      get {
        return snapshot["id"]! as! GraphQLID
      }
      set {
        snapshot.updateValue(newValue, forKey: "id")
      }
    }
  }
}