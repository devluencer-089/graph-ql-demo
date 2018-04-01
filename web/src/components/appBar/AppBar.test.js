import React from 'react';
import { shallow } from 'enzyme';
import AppBar from './AppBar';

describe('AppBar', () => {
  it('should match snapshot', () => {
    let eut = shallow(<AppBar />);

    expect(eut).toMatchSnapshot();
  });
});
