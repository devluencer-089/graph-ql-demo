import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Card, { CardActions, CardContent, CardHeader } from 'material-ui/Card';
import Button from 'material-ui/Button';
import Typography from 'material-ui/Typography';

const styles = theme => ({
  card: {
    minWidth: 275,
    margin: theme.spacing.unit
  }
});

function EmployeeCard(props) {
  const { classes, employee } = props;
  const fullname = `${employee.firstname} ${employee.lastname}`;
  const level = translateLevel(employee.level);
  return (
    <Card className={classes.card}>
      <CardHeader title={fullname} subheader={level} />
      <CardContent>
        <Typography component="p">{employee.phoneNumber}</Typography>
      </CardContent>
      <CardActions>
        <Button size="small">Details</Button>
      </CardActions>
    </Card>
  );
}

EmployeeCard.propTypes = {
  classes: PropTypes.object.isRequired,
  employee: PropTypes.object.isRequired
};

function translateLevel(level) {
  switch (level) {
    case 'JUNIOR_DEVELOPER':
      return 'Junior Developer';
    case 'SENIOR_DEVELOPER':
      return 'Senior Developer';
    case 'ARCHITECT':
      return 'Architect';
    case 'LEAD_DEVELOPER':
      return 'Lead Developer';
    default:
      return 'Unpositioned';
  }
}

export default withStyles(styles)(EmployeeCard);
