#include <stdio.h>
#include <stdlib.h>

#define BIGNUM 20;
void index_m(int ary[], float fary[]);

int main()
{
    int intary[10];
    float fltary[10];
    index_m(intary, fltary);
    return 0;
}

void index_m(int ary[], float fary[])
{
    int i;
    float f = 3.14;
    for(i=0;i<BIGNUM;i++)
    {
        ary[i]=i;
        fary[i]=i*f;
    }
}

