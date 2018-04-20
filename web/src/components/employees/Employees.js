import React, { Component } from 'react';
import { graphql } from 'react-apollo';
import gql from 'graphql-tag';
import { withStyles } from 'material-ui/styles';

import EmployeeCard from './EmployeeCard';

export const query = gql`
  query EmployeeQuery {
    version
    allEmployees {
      id
      level
      firstname
      lastname
      phoneNumber
    }
  }
`;

const styles = theme => ({
  list: {
    [theme.breakpoints.up('md')]: {
      display: 'flex'
    },
    flexWrap: 'wrap'
  }
});

export class Employees extends Component {
  render() {
    const { data: { allEmployees, loading }, classes } = this.props;

    if (loading) {
      return <div>Loading...</div>;
    }

    return (
      <div className={classes.list}>
        {allEmployees.map(employee => (
          <div key={employee.id}>
            <EmployeeCard employee={employee} />
          </div>
        ))}
      </div>
    );
  }
}

export default graphql(query)(withStyles(styles)(Employees));
