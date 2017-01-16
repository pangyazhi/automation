import {SimpleObject} from './simple-object';

describe('SimpleObject', () => {
  it('should create an instance', () => {
    expect(new SimpleObject()).toBeTruthy();
  });
  it('should accept values in the constructor', () =>{
    let simpleObject = new SimpleObject({
      name: 'simple', description: 'simple object test'
    });
    expect (simpleObject.name).toEqual('simple');
    expect (simpleObject.description).toEqual('simple object test');
  });
});
