import React from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';

import './App.css';

import AppBar from './components/appBar/AppBar';
import Projects from './components/projects/Projects';
import Employees from './components/employees/Employees';
import EmployeeDetails from './components/employees/EmployeeDetails';

const styles = {
  main: {
    display: 'flex',
    flexDirection: 'column'
  }
};

const withAppBar = WrappedComponent => props => (
  <AppBar>
    <WrappedComponent {...props} />
  </AppBar>
);

const ProjectsWithAppBar = withAppBar(Projects);
const EmployeesWithAppBar = withAppBar(Employees);
const EmployeeDetailsWithAppBar = withAppBar(EmployeeDetails);

const App = () => (
  <div style={styles.main}>
    <Switch>
      <Route exact path="/employees" component={EmployeesWithAppBar} />
      <Route
        exact
        path="/employees/:employeeId"
        component={EmployeeDetailsWithAppBar}
      />

      <Route exact path="/projects" component={ProjectsWithAppBar} />
      <Route render={() => <Redirect to="/employees" />} />
    </Switch>
  </div>
);

export default App;
