var updatePosition = function(spx,spy){
    var x = 0;
    var y = 0
    x += spx;
    y += spy;
    return [x,y]
}

describe("A suite is just a function", function() {
  var a;

  it("and so is a spec", function() {

    expect(updatePosition(1,-1)).toEqual([1,-1]);
    expect(updatePosition(1,1)).toEqual([1,1]);
    expect(updatePosition(-1,1)).toEqual([-1,1]);
  });
});
