package rendering

import board.Board
import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.util.*

class GeometryCanvas(private val board: Board) : Canvas() {

    init {
        setSize(1600, 2000)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                repaint()
            }
        }, 0, 10000)
    }


    override fun paint(g: Graphics) {
        super.paint(g)
        g as Graphics2D
        board.paint(g)
    }
}

class CanvasRenderer(private val canvas: Canvas) {

    private val mainFrame: Frame = Frame("Geometry")
    private val controlPanel: Panel = Panel()

    init {

        prepareGUI()
    }

    private fun prepareGUI() {
        mainFrame.setSize(1600, 2000)
        mainFrame.layout = GridLayout(1, 1)
        mainFrame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(windowEvent: WindowEvent?) {
                System.exit(0)
            }
        })
        controlPanel.layout = FlowLayout()
        controlPanel.add(canvas)
        mainFrame.add(controlPanel)
        mainFrame.isVisible = true
    }
}