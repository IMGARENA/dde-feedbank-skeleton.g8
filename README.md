# Feedbank skeleton template

## Purpose

Quickstart a feedbank service from scratch according to standard usage, using giter8.


## Usage

```bash
sbt new IMGARENA/dde-feedbank-skeleton.g8
```

...and then fill out the properties for your new service. There's no need to clone this repository
manually, it will be done by sbt.

For more info, see the
[sbt new docs](https://www.scala-sbt.org/1.x/docs/sbt-new-and-Templates.html#sbt+new+and+Templates)

## Next steps

Once you've created your base project, you should search for `FIXME` and read the relevant comments, which
will guide you to make project-specific decisions and amendments to what comes out of the box. This will
highlight where you need to fill in your implementations, where you might consider moving or restructuring
the defaults, and anything else which needs specific attention.

## Troubleshooting

- Note that despite your feed name being represented as `matchDetails` in the code, for example, you must
provide multi-word names in full form like `match details` for them to be correctly camelcased by the
template.
