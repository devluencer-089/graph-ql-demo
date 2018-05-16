import React, { Component } from 'react';
import { Query } from 'react-apollo';
import gql from 'graphql-tag';
import { withStyles } from 'material-ui/styles';
import List from 'material-ui/List';
import ListItem from 'material-ui/List/ListItem';
import Typography from 'material-ui/Typography';

export const GET_ALL_PROJECTS = gql`
  {
    allProjects {
      id
      name
      cstLead {
        firstname
        lastname
      }
      rating
      profit
    }
  }
`;

const styles = theme => ({
  root: {
    width: '100%',
    backgroundColor: theme.palette.background.paper
  }
});

export class Projects extends Component {
  render() {
    return (
      <Query query={GET_ALL_PROJECTS}>
        {({ loading, error, data }) => {
          if (loading) return 'Loading...';
          if (error) return `Error! ${error.message}`;

          const { allProjects: projects } = data;
          return (
            <List className={this.props.classes.root}>
              {projects.map(project => (
                <ListItem key={project.id}>
                  <div>
                    <Typography variant="headline">{project.name}</Typography>
                    <Typography variant="body" component="p">
                      Rating: {project.rating}
                    </Typography>
                    <Typography variant="body" component="p">
                      Profit: {project.profit}
                    </Typography>
                  </div>
                </ListItem>
              ))}
            </List>
          );
        }}
      </Query>
    );
  }
}

export default withStyles(styles)(Projects);
