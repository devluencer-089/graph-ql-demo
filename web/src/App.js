import React from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';

import './App.css';

import AppBar from './components/appBar/AppBar';
import Users from './components/users/Users';

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

const UsersWithAppBar = withAppBar(Users);

const App = () => (
  <div style={styles.main}>
    <Switch>
      <Route exact path="/users" component={UsersWithAppBar} />
      <Route render={() => <Redirect to="/users" />} />
    </Switch>
  </div>
);

export default App;
