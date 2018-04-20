import React from 'react';
import { shallow } from 'enzyme';
import { Employees, query } from './Employees';

describe('Employees Component', () => {
  let data = {
    allEmployees: [
      {
        id: '3',
        level: 'SENIOR_DEVELOPER',
        firstname: 'Si',
        lastname: 'Tran'
      },
      {
        id: '2',
        level: 'SENIOR_DEVELOPER',
        firstname: 'Michael',
        lastname: 'Sewell'
      },
      {
        id: '1',
        level: 'ARCHITECT',
        firstname: 'Michael',
        lastname: 'Omann'
      }
    ]
  };

  it('should match snapshot', () => {
    let eut = shallow(<Employees data={data} classes={{}} />);

    expect(eut).toMatchSnapshot();
  });

  it('should contain employee cards', () => {
    let eut = shallow(<Employees data={data} classes={{}} />);

    expect(eut.find('WithStyles(EmployeeCard)')).toHaveLength(3);
  });

  describe('GraphQL Query', () => {
    it('should match snapshot', () => {
      expect(query).toMatchSnapshot();
    });
  });
});
