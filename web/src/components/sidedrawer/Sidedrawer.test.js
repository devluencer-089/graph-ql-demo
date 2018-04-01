import React from 'react';
import { createShallow } from 'material-ui/test-utils';
import Sidedrawer from './Sidedrawer';

describe('AppBar', () => {
  let shallow = createShallow({ dive: true });
  it('should match snapshot', () => {
    let eut = shallow(<Sidedrawer />);

    expect(eut).toMatchSnapshot();
  });
});
