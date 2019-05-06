distance = function(x1,x2,y1,y2){
  return Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
}

describe("A suite is just a function", function() {
  var a;

  it("and so is a spec", function() {

    expect(distance(4,-4,3,-3)).toBe(10);
    expect(distance(2,3,4,5)).toBe(1.4142135623730951);
    expect(distance(4,-4,3,-3)).toBe(10);
    expect(distance(2,3,4,5)).toBe(1.4142135623730951);
  });
});
