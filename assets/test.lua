luajava.loadLib("mg.land.Main", "open")

print("I've been called!")

proxytest = {}
function proxytest.Invoke(var1, var2, var3) 
	print(var1 .. var2 .. var3 .. "\n")
end

proxy = luajava.createProxy("mg.land.event.EventListener", proxytest)

testEvent = luajava.newInstance("mg.land.event.TestEvent")
testEvent:AddListener(proxy)