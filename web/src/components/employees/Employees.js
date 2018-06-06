import React from 'react';
import { Query } from 'react-apollo';
import gql from 'graphql-tag';
import { withStyles } from 'material-ui/styles';

import EmployeeCard from './EmployeeCard';

export const query = gql`
  {
    query
    goes
    here
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

function Employees(props) {
  // return <Query query={}/>

  return <div />;
}

export default withStyles(styles)(Employees);
