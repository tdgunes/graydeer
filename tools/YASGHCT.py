print "YASGHCT - Yet Another Small GrayDeer Homework Creation Tool"
#great name by the way ;)
alist = []
while True:
    assignment = raw_input("Assignment Name: ")
    status = raw_input("Status: ")
    actions = raw_input("Actions: ")
    grade = raw_input("Grade: ")
    draft= [assignment,status,actions,grade]
    anew = "**".join(draft)
    alist.append(anew)
    print "*************************"
    print "******* COPY THIS *******"
    print "*************************"
    print "+=+\n".join(alist)    
    print "*************************"