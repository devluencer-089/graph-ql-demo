import React from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';

import './App.css';

import AppBar from './components/appBar/AppBar';
import Employees from './components/employees/Employees';

const styles = {
  main: {
    display: 'flex',
    flexDirection: 'column'
  }
};

const withAppBar = WrappedComponent => () => (
  <AppBar>
    <WrappedComponent />
  </AppBar>
);

const EmployeesWithAppBar = withAppBar(Employees);

const App = () => (
  <div style={styles.main}>
    <Switch>
      <Route exact path="/employees" component={EmployeesWithAppBar} />
      <Route render={() => <Redirect to="/employees" />} />
    </Switch>
  </div>
);

export default App;
